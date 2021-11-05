package com.disqo.calendly_service.service.dto;

import com.fasterxml.jackson.annotation.JsonValue;

public class SubscribeDTO {

    @JsonValue()
    private String url;
    private String[] events;
    private String organization;
    private String user;
    private String scope;
    private String signing_key;

//    {
//        "url": "https://blah.foo/bar",
//            "events": [
//        "invitee.created",
//                "invitee.canceled"
//  ],
//        "organization": "https://api.calendly.com/organizations/AAAAAAAAAAAAAAAA",
//            "user": "https://api.calendly.com/users/BBBBBBBBBBBBBBBB",
//            "scope": "user",
//            "signing_key": "5mEzn9C-I28UtwOjZJtFoob0sAAFZ95GbZkqj4y3i0I"
//    }
}
