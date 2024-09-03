package com.isi.auth_network.handler;

import java.util.Map;

public record ErrorResponse (
        Map<String,String> errors
) {
}
