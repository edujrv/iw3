package org.magm.backend.auth.filters;

public final class AuthConstants {
	public static final long EXPIRATION_TIME = (60 * 60 * 1000);
	public static final String SECRET = "HyLLzejPj@ynEf6LxUwM8T!z&vBHqZmf2T*N%y56ZKv3*Y8qt4";

	public static final String AUTH_HEADER_NAME = "Authorization";
	public static final String AUTH_PARAM_NAME = "authtoken";
	public static final String TOKEN_PREFIX = "Bearer ";
}
