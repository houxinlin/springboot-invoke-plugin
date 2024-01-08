package com.hxl.plugin.springboot.invoke;

import com.hxl.plugin.springboot.invoke.bean.RequestMappingWrapper;
import com.hxl.plugin.springboot.invoke.model.*;
import com.intellij.util.messages.Topic;

public class IdeaTopic {
    public static final Topic<ScheduledChooseEventListener> SCHEDULED_CHOOSE_EVENT = new Topic<>("SCHEDULED_CHOOSE_EVENT", ScheduledChooseEventListener.class);
    public static final Topic<ControllerChooseEventListener> CONTROLLER_CHOOSE_EVENT = new Topic<>("CONTROLLER_CHOOSE_EVENT", ControllerChooseEventListener.class);
    public static final Topic<HttpResponseEventListener> HTTP_RESPONSE = new Topic<>("HTTP_RESPONSE", HttpResponseEventListener.class);
    public static final Topic<HttpResponseEventListener> HTTP_REQUEST_CANCEL = new Topic("HTTP_REQUEST_CANCEL", HttpRequestCancelEventListener.class);
    public static final Topic<DeleteAllDataEventListener> DELETE_ALL_DATA = new Topic<>("DELETE_ALL_DATA", DeleteAllDataEventListener.class);
    public static final Topic<ClearRequestCacheEventListener> CLEAR_REQUEST_CACHE = new Topic<>("CLEAR_REQUEST_CACHE", ClearRequestCacheEventListener.class);
    public static final Topic<SpringRequestMappingModel> ADD_SPRING_REQUEST_MAPPING_MODEL = new Topic<>("ADD_SPRING_REQUEST_MAPPING_MODEL", SpringRequestMappingModel.class);
    public static final Topic<SpringScheduledModel> ADD_SPRING_SCHEDULED_MODEL = new Topic<>("ADD_SPRING_SCHEDULED_MODEL", SpringScheduledModel.class);
    public static final Topic<BaseListener> DELETE_ALL_REQUEST = new Topic<>("DELETE_ALL_REQUEST", BaseListener.class);
    public static final Topic<BaseListener> CHANGE_LAYOUT = new Topic<>("CHANGE_LAYOUT", BaseListener.class);
    public static final Topic<ScriptLogListener> SCRIPT_LOG = new Topic<>("SCRIPT_LOG", ScriptLogListener.class);
    public static final Topic<BaseListener> LANGUAGE_CHANGE = new Topic<>("LANGUAGE_CHANGE", BaseListener.class);
    public static final Topic<BaseListener> ENVIRONMENT_ADDED = new Topic<>("ENVIRONMENT_ADDED", BaseListener.class);
    public static final Topic<BaseListener> ENVIRONMENT_CHANGE = new Topic<>("ENVIRONMENT_CHANGE", BaseListener.class);

    @FunctionalInterface
    public interface BaseListener {
        void event();
    }

    public interface ScriptLogListener {
        void log(String id, String value);

        void clear(String id);
    }

    @FunctionalInterface
    public interface SpringScheduledModel {
        void addSpringScheduledModel(ScheduledModel scheduledModel);
    }

    @FunctionalInterface
    public interface ClearRequestCacheEventListener {
        void onClearEvent(String id);
    }

    @FunctionalInterface
    public interface SpringRequestMappingModel {
        void addRequestMappingModel(RequestMappingModel springInvokeEndpoint,boolean dynamic);
    }

    public interface HttpResponseEventListener {
        void onResponseEvent(String requestId, InvokeResponseModel invokeResponseModel);
    }

    @FunctionalInterface
    public interface HttpRequestCancelEventListener {
        void onCancelEvent(String requestId);
    }

    public interface ControllerChooseEventListener {
        void onChooseEvent(RequestMappingWrapper requestId);
        void refreshEvent(RequestMappingModel requestMappingModel);
    }

    @FunctionalInterface
    public interface DeleteAllDataEventListener {
        void onDelete();
    }

    @FunctionalInterface
    public interface ScheduledChooseEventListener {
        void onChooseEvent(SpringScheduledSpringInvokeEndpoint springScheduledSpringInvokeEndpoint, int port);
    }
}
