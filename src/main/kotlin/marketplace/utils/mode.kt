fun isDevelopment(): Boolean {
  return System.getenv("DEVELOPMENT") == "true"
}