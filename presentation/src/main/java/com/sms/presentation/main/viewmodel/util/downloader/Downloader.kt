package com.sms.presentation.main.viewmodel.util.downloader

interface Downloader {
    fun downloadFile(url: String): Long
}