package com.tthieu.myhelsinki.common.data.api.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiEvent(
    @Json(name = "description")
    val description: ApiEventDescription?,
    @Json(name = "event_dates")
    val eventDates: ApiEventDates?,
    @Json(name = "id")
    val id: String?,
    @Json(name = "info_url")
    val infoUrl: String?,
    @Json(name = "location")
    val location: ApiEventLocation?,
    @Json(name = "modified_at")
    val modifiedAt: String?,
    @Json(name = "name")
    val name: ApiEventName?,
    @Json(name = "source_type")
    val sourceType: ApiEventSourceType?,
    @Json(name = "tags")
    val tags: List<ApiEventTag?>?
)

@JsonClass(generateAdapter = true)
data class ApiEventDescription(
    @Json(name = "body")
    val body: String?,
    @Json(name = "images")
    val images: List<ApiEventImage?>?,
    @Json(name = "intro")
    val intro: String?
)

@JsonClass(generateAdapter = true)
data class ApiEventAddress(
    @Json(name = "locality")
    val locality: String?,
    @Json(name = "neighbourhood")
    val neighbourhood: String?,
    @Json(name = "postal_code")
    val postalCode: String?,
    @Json(name = "street_address")
    val streetAddress: String?
)

@JsonClass(generateAdapter = true)
data class ApiEventDates(
    @Json(name = "additional_description")
    val additionalDescription: Any?,
    @Json(name = "ending_day")
    val endingDay: String?,
    @Json(name = "starting_day")
    val startingDay: String?
)

@JsonClass(generateAdapter = true)
data class ApiEventImage(
    @Json(name = "copyright_holder")
    val copyrightHolder: String?,
    @Json(name = "license_type")
    val licenseType: ApiEventLicenseType?,
    @Json(name = "media_id")
    val mediaId: String?,
    @Json(name = "url")
    val url: String?
)

@JsonClass(generateAdapter = true)
data class ApiEventLicenseType(
    @Json(name = "id")
    val id: Int?,
    @Json(name = "name")
    val name: String?
)

@JsonClass(generateAdapter = true)
data class ApiEventLocation(
    @Json(name = "address")
    val address: ApiEventAddress?,
    @Json(name = "lat")
    val lat: Double?,
    @Json(name = "lon")
    val lon: Double?
)

@JsonClass(generateAdapter = true)
data class ApiEventName(
    @Json(name = "en")
    val en: String?,
    @Json(name = "fi")
    val fi: String?,
    @Json(name = "sv")
    val sv: String?,
    @Json(name = "zh")
    val zh: String?
)

@JsonClass(generateAdapter = true)
data class ApiEventSourceType(
    @Json(name = "id")
    val id: Int?,
    @Json(name = "name")
    val name: String?
)

@JsonClass(generateAdapter = true)
data class ApiEventTag(
    @Json(name = "id")
    val id: String?,
    @Json(name = "name")
    val name: String?
)

// @JsonClass(generateAdapter = true)
// data class ApiEventTags(
//     @Json(name = "linkedevents:helmet:1")
//     val linkedeventsHelmet1: String?,
//     @Json(name = "linkedevents:helmet:10592")
//     val linkedeventsHelmet10592: String?,
//     @Json(name = "linkedevents:helmet:10689")
//     val linkedeventsHelmet10689: String?,
//     @Json(name = "linkedevents:helmet:10691")
//     val linkedeventsHelmet10691: String?,
//     @Json(name = "linkedevents:helmet:10847")
//     val linkedeventsHelmet10847: String?,
//     @Json(name = "linkedevents:helmet:10851")
//     val linkedeventsHelmet10851: String?,
//     @Json(name = "linkedevents:helmet:11683")
//     val linkedeventsHelmet11683: String?,
//     @Json(name = "linkedevents:helmet:11699")
//     val linkedeventsHelmet11699: String?,
//     @Json(name = "linkedevents:helmet:11733")
//     val linkedeventsHelmet11733: String?,
//     @Json(name = "linkedevents:helmet:11767")
//     val linkedeventsHelmet11767: String?,
//     @Json(name = "linkedevents:yso:p14004")
//     val linkedeventsYsoP14004: String?,
//     @Json(name = "linkedevents:yso:p2149")
//     val linkedeventsYsoP2149: String?,
//     @Json(name = "linkedevents:yso:p556")
//     val linkedeventsYsoP556: String?,
//     @Json(name = "linkedevents:yso:p9270")
//     val linkedeventsYsoP9270: String?
// )