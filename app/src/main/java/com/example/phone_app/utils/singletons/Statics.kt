package com.example.phone_app.utils.singletons

import android.Manifest

object Statics {
    //public static final String baseUrl = "http://public.arx.net/~sergios/p4seniors/";
    //public static final String baseUrl = "https://public.arx.net/~paulg/wearefamily/";
    //public static final String baseUrl = "https://public.arx.net/wearefami.ly/dev.admin/";

    // FOR PILLS TESTING ASK GEORGE TO CHANGE URL

    const val PREFS_FILE = "login"

    // 48x48 pill images
    // https://public.arx.net/wearefami.ly/dev/images/pills/pill_c7e400.png
    const val FONT_TYPE = "RobotoCondensed-Bold.ttf"

    /**
     * FRAGMENT CONSTANTS
     */
    const val FRAG = "frag"
    const val MAIN_FRAGMENT_BACKSTACK = "frag_stack"

    /**
     * EASY SETUP SETTINGS
     */
    // MODE 1-2
    val APPLICATION_PERMISSIONS = arrayOf(
        Manifest.permission.READ_CONTACTS,
        Manifest.permission.CALL_PHONE,
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_NOTIFICATION_POLICY,
        Manifest.permission.CAMERA,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.READ_SMS,
        Manifest.permission.SEND_SMS,
        Manifest.permission.READ_CALL_LOG/*,
        Manifest.permission.READ_PHONE_STATE*/
    )


    // MODE 3
    val APPLICATION_PERMISSIONS_THIRD_MODE = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.CAMERA,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE/*,
        Manifest.permission.READ_PHONE_STATE*/
    )

    const val CONTACTS_SET = "contacts_set"
    const val CONTACTS_EMERGENCY_SET = "contacts_emergency_set"
    const val APPLICATIONS_SET = "applications_set"

    /**
     * SCOPES
     */
    const val SCOPE_SELECT_PILLS = "SCOPE_SELECT_PILLS"
    const val SCOPE_INSTANCE_SELECT_PILLS = "SCOPE_INSTANCE_SELECT_PILLS"

    /**
     * PREFERENCES CONSTANTS
     */
    const val PREFERENCES_GRANTED_PERMISSIONS = "PREFERENCES_GRANTED_PERMISSIONS"
    const val PREFERENCES_LANGUAGE = "PREFERENCES_LANGUAGE"
    const val PREFERENCES_USER_ID = "user_id"
    const val PREFERENCES_DEVICE_ID = "device_id"
    const val PREFERENCES_CHILD_ID = "childid"
    const val PREFERENCES_CHILDNAME = "childname"
    const val PREFERENCES_UUID = "UUID"
    const val PREFERENCES_PASSWORD = "password"
    const val PREFERENCES_NAME = "name"
    const val PREFERENCES_LASTNAME = "lastname"
    const val PREFERENCES_MODE = "mode"
    const val PREFERENCES_THEME = "theme"
    const val PREFERENCES_SAFEMODE = "safemode"
    const val PREFERENCES_LOGGEDIN = "loggedin"
    const val PREFERENCES_EXISTS = "exists"
    const val PREFERENCES_LOCATION_TIME_POSITION = "timePosition"
    const val PREFERENCES_LOCATIONENABLED = "locationEnabled"
    const val PREFERENCES_LOCATION_INTERVAL = "location_interval"
    const val PREFERENCES_EMAIL = "email"
    const val PREFERENCES_ACTIVE = "active"
    const val PREFERENCES_BF_NAME = "bf_name"
    const val PREFERENCES_WAF_TOKEN = "token"

    /**
     * Notification action
     */
    const val NOTIFICATION_ACTION_LOGOUT = "NOTIFICATION_ACTION_LOGOUT"
    const val NOTIFICATION_ACTION_PILL_TAKE = "NOTIFICATION_ACTION_PILL_TAKE"
    const val NOTIFICATION_ACTION_PILL_LATER = "NOTIFICATION_ACTION_PILL_LATER"
    const val NOTIFICATION_ACTION_PILL_SKIP = "NOTIFICATION_ACTION_PILL_SKIP"

    /**
     * BUNDLE AND EXTRAS CONSTANTS
     */
    const val EXTRA_PILL_PHOTO = "EXTRA_PILL_PHOTO"
    const val EXTRA_PILL_ACTION = "EXTRA_PILL_ACTION"
    const val BUNDLE_EXISTING_PILL = "existing_pill"
    const val BUNDLE_ID = "id"
    const val BUNDLE_DOSE_ID = "dose_id"
    const val BUNDLE_MESSAGE_ID = "MESSAGE_ID"
    const val BUNDLE_CONTACT_ADDRESS = "contact_address"
    const val BUNDLE_CONTACT_NUMBER = "contact_number"
    const val BUNDLE_SELECT_CONTACT = "SELECT_CONTACT"
    const val BUNDLE_PILL_NAME = "PILL_NAME"
    const val BUNDLE_PILL_IMAGE = "PILL_IMAGE"
    const val BUNDLE_PILL_ID = "PILL_ID"
    const val BUNDLE_PILL_DOSE_INTERVAL = "PILL_DOSE_INTERVAL"
    const val BUNDLE_PILL_STATUS = "PILL_STATUS"
    const val BUNDLE_PILL_NOTIFICATION_ID = "PILL_NOTIFICATION_ID"
    const val BUNDLE_PILL_TIME = "PILL_TIME"

    /**
     * PILL ACTIONS CONSTANTS
     */
    const val PILL_ACTION_ADD = "add"
    const val PILL_ACTION_DELETE = "delete"
    const val PILL_ACTION_UPDATE = "update"
    const val PILL_ACTION_GET = "get"
    const val PILL_ACTION_PRESCRIPTION = "prescription"

    /**
     * REQUEST ACTIONS
     */
    const val CHECK_ACTION = "action"
    const val ACTION_REGISTER = "register_user"
    const val ACTION_CHECK = "check"
    const val ACTION_SET = "set"
    const val ACTION_INSERT = "insert"
    const val ACTION_CHECK_DEVICE = "check_device"
    const val ACTION_CHECK_TABLET = "check_tablet"
    const val ACTION_CREATE = "create"
    const val ACTION_READ = "read"
    const val ADD_RECIPE = "add_recipe"
    const val VERSION_APP = "app_version"
    const val TYPE_DEVICE = "device_type"

    /**
     * Fitbit OAuth 2.0 fields
     */
    /*const val PREFERENCES_CODE = "code"
    const val PREFERENCES_TOKEN = "token"
    const val PREFERENCES_REFRESH_TOKEN = "refresh_token"
    const val PREFERENCES_EXPIRES_IN = "expires_in"
    const val PREFERENCES_TOKEN_TYPE = "token_type"
    const val PREFERENCES_FITBIT_USER_ID = "user_id"
    const val PREFERENCES_SCOPE = "scope"

    const val CLIENT_CODE = "client_code"
    const val AUTHORIZATION_GRANT_TYPE_AUTHORIZATION_CODE = "authorization_code"
    const val AUTHORIZATION_GRANT_TYPE_REFRESH_TOKEN = "refresh_token"

    const val SCOPE_ACTIVITY = "activity"
    const val SCOPE_HEARTRATE = "heartrate"
    const val SCOPE_LOCATION = "location"
    const val SCOPE_NUTRITION = "nutrition"
    const val SCOPE_PROFILE = "profile"
    const val SCOPE_SETTINGS = "settings"
    const val SCOPE_SLEEP = "sleep"
    const val SCOPE_WEIGHT = "weight"*/

    const val PROMPT_EDIT = "edit"
    const val PROMPT_DELETE = "delete"
    const val IMAGE_PATH = "image_path"
    const val IMAGE_DATE = "image_date"

    /**
     * Game names
     */
    const val GAME_CORRECT_ORDER = "Correct Order"
    const val GAME_GUESS_THE_NUMBER = "Guess the Number"
    const val GAME_GUESS_THE_PATTERN = "Guess The Patter"
    const val GAME_MEMORY_MATCH = "Memory Match"
    const val GAME_PHOTO_QUIZ = "Photo Quiz"
    const val GAME_FIND_THE_DIFFERENCE = "Find the difference"
    const val GAME_FISH = "Fish"
    const val GAME_CARDS = "Cards"
    const val GAME_CARD_MATCH = "Card Match"
    const val GAME_COLORED_SHAPES = "Colored Shapes"
}
