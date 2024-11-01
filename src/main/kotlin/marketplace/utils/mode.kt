package marketplace.utils

fun isDevelopment(): Boolean {
  return System.getenv("DEVELOPMENT") == "true"
}