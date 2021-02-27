package com.zandero.rest.authorization;

import com.zandero.rest.test.data.SimulatedUser;
import io.vertx.core.*;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.User;
import io.vertx.ext.auth.authentication.*;

public class MyAuthenticator implements AuthenticationProvider {

    @Override
    public void authenticate(JsonObject credentials, Handler<AsyncResult<User>> resultHandler) {
        String token = credentials != null ? credentials.getString("token") : null;
        if (token != null) {
            resultHandler.handle(Future.succeededFuture(new SimulatedUser(token)));
        } else {
            resultHandler.handle(Future.failedFuture("Missing authentication token"));
        }
    }

    @Override
    public void authenticate(Credentials credentials, Handler<AsyncResult<User>> resultHandler) {
        if (credentials instanceof TokenCredentials) {
            TokenCredentials token = (TokenCredentials) credentials;
            authenticate(token.toJson(), resultHandler);
        } else {
            resultHandler.handle(Future.failedFuture(new IllegalArgumentException("Missing authentication token")));
        }
    }
}
