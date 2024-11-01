package marketplace.core.authorization

import marketplace.core.secrets.SecretManager
import org.mindrot.jbcrypt.BCrypt.hashpw

class PasswordService {
  val salt = SecretManager.getSecret("3155b582-4894-42da-a2e8-b21b0161a1a6")

  fun verify(password: String, hash: String): Boolean {
    return hash == hashpw(password, salt)
  }

  fun hashPassword(password: String): String {
    return hashpw(password, salt)
  }
}