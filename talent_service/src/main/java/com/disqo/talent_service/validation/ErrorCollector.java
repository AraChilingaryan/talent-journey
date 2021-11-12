package com.disqo.talent_service.validation;

import java.util.Collection;

public interface ErrorCollector {

    Collection<String> collectDefaultMessages(ValidationError error);

    String defaultMessagesAsJson(ValidationError error);

}