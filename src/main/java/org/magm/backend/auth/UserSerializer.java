package org.magm.backend.auth;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.magm.backend.auth.User;
import org.magm.backend.auth.filters.AuthConstants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class UserSerializer extends StdSerializer<User> {


    public UserSerializer(Class<User> t, boolean dummy) {
        super(t, dummy);
    }

    @Override
    public void serialize(User value, JsonGenerator gen, SerializerProvider provider) throws IOException {

        String token = JWT.create().withSubject(value.getUsername())
                .withClaim("internalId", value.getIdUser())
                .withClaim("roles", new ArrayList<String>(value.getAuthoritiesStr()))
                .withClaim("email", value.getEmail())
                .withClaim("version", "1.0.0")
                .withExpiresAt(new Date(System.currentTimeMillis() + AuthConstants.EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(AuthConstants.SECRET.getBytes()));

        gen.writeStartObject();
        gen.writeStringField("User", value.getUsername());
        gen.writeObjectField("Roles", value.getRoles());
        gen.writeStringField("EMail", value.getEmail());
        gen.writeStringField("Authtoken", token);
        gen.writeEndObject();

    }
}
