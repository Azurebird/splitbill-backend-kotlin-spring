

import com.splitbill.auth.service.token.TokenService
import io.jsonwebtoken.Clock
import io.jsonwebtoken.Jwts
import org.joda.time.DateTime
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.*
import io.jsonwebtoken.Claims
import io.jsonwebtoken.security.Keys

@Service
class JWTTokenService(
    @Value("\${jwt.issuer:splitbill}") val issuer: String,
    @Value("\${jwt.expiration-sec:86400}") val expirationSec: Int,
    @Value("\${jwt.clock-skew-sec:300}") val clockSkewSec: Int,
    @Value("\${jwt.secret:thisismysecretpasswordof32chars.}") secret: String
) : TokenService, Clock {

    private val secretKey = Keys.hmacShaKeyFor(secret.toByteArray())

    // Just when compression is needed
    // private val compressionCodec = GzipCompressionCodec()

    override fun permanentToken(attributes: Map<String, String>): String {
        return createNewToken(attributes, 0)
    }

    override fun expiringToken(attributes: Map<String, String>): String {
        return createNewToken(attributes, expirationSec)
    }

    override fun untrusted(token: String): Map<String, String> {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override fun verify(token: String): Map<String, String> {
        val parser = Jwts
                .parser()
                .requireIssuer(issuer)
                .setClock(this)
                .setAllowedClockSkewSeconds(clockSkewSec.toLong())
                .setSigningKey(secretKey)
        return claimsToMap(parser.parseClaimsJws(token).body)
    }

    override fun now(): Date {
        val now = DateTime.now()
        return now.toDate()
    }

    /**
     * Creates a new jwt token with the issuer given at this class creation with the expiration date in the future.
     * Attributes are added to the token creation
     *
     * @param attributes
     * @param expiresInSec
     * @return
     */
    private fun createNewToken(attributes: Map<String, String>, expiresInSec: Int): String {
        val now = DateTime()
        val claims = Jwts.claims()
                .setIssuer(issuer)
                .setIssuedAt(now.toDate())

        if (expiresInSec > 0) {
            val expiresAt = now.plusSeconds(expiresInSec)
            claims.expiration = expiresAt.toDate()
        }
        claims.putAll(attributes)

        return Jwts
            .builder()
            .setClaims(claims)
            .signWith(secretKey)
            // .compressWith(compressionCodec)
            .compact()
    }

    private fun claimsToMap(toClaims: Claims): Map<String, String> {
        val builder = mutableMapOf<String, String>()
        val claims = toClaims.entries
        for (e in claims) {
            builder[e.key] = e.value.toString()
        }
        return builder
    }
}