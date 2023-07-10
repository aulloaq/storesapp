package com.aulloaq.storesapp

object AppConfig {
    fun getBaseUrl(): String = BuildConfig.BASE_URL
    fun getApiToken(): String = BuildConfig.API_TOKEN
    fun getCompanyId(): String = BuildConfig.COMPANY_ID
}