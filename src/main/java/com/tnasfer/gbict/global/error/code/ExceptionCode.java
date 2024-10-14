package com.tnasfer.gbict.global.error.code;

import lombok.Getter;

@Getter
public enum ExceptionCode {
    BAD_REQUEST_SORT_DATA(400,"Invalid format for sorting data"),
    ACCESS_DENIED(401,"This account is inaccessible"),
    INVALID_TOKEN(401,"is not the same token as data"),
    NO_AUTH_HEADER(401,"Authentication is not in header or no bearer in Token"),
    UNAUTHORIZED(401,"invalid token Data"),
    TOKEN_NOT_FOUND(401, "Refresh token not found"),
    EXPIRED_TOKEN(401,"Token has expired"),
    EXPIRED_REFRESH_TOKEN(401,"Refresh token has expired"),
    ACCOUNT_RESTRICTED(403,"oauth account Member"),
    MEMBER_NOT_FOUND(404, "Member not found"),
    MEMBER_EXISTS(409,"Member EXISTS"),
    ALREADY_OAUTH_MEMBER(409, "This email already used in OAuth2"),
    MEMBER_MISMATCH(403,"The login Member and the author are different"),
    MEMBER_WITHDRAWN(409, "Already with drawn Member"),
    URL_NOT_FOUND(404, "This Url not found"),
    DOCUMENT_NOT_FOUND(404,"Document not found"),
    MUSIC_NOT_FOUND_INS3(404,"Music not found in s3 server"),
    HIDDEN_MUSIC(403,"Inactive music"),
    MUSIC_EXISTS(409,"Music EXISTS"),
    COMMENT_NOT_FOUND(404, "Comment not found"),
    PASSWORD_MISMATCH(404, "Password is not Correct"),
    DOCUMENT_TITLE_EXISTS(409, "document title EXISTS"),
    ALREADY_INACTIVE_MUSIC(409, "Already inactive Music"),
    ALREADY_ACTIVE_MUSIC(409, "Already active Music");

    private final int status;
    private final String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }
}
