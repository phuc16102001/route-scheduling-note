package com.phuc.routeschedulingnote.support.error;

public enum ExitCode {
    E500,
    E401,       // Unauthorized
    E403,       // Access denied
    E404,       // Not found resource
    E1000,      // Conflict place in schedule
    E2000,      // Username existed
    E2001       // Username not found
}
