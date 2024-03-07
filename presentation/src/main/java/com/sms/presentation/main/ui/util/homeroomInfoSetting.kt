package com.sms.presentation.main.ui.util

import com.sms.presentation.main.viewmodel.TeacherViewModel

fun homeroomInfoSetting(
    gradeInfo: String, classInfo: String, viewModel: TeacherViewModel
){
    when(gradeInfo){
        "1학년" -> {
            when(classInfo){
                "1반" -> {
                    viewModel.homeroom(1,1)
                }
                "2반" -> {
                    viewModel.homeroom(1,2)
                }
                "3반" -> {
                    viewModel.homeroom(1,3)
                }
                "4반" -> {
                    viewModel.homeroom(1,4)
                }
            }
        }
        "2학년" -> {
            when(classInfo){
                "1반" -> {
                    viewModel.homeroom(2,1)
                }
                "2반" -> {
                    viewModel.homeroom(2,2)
                }
                "3반" -> {
                    viewModel.homeroom(2,3)
                }
                "4반" -> {
                    viewModel.homeroom(2,4)
                }
            }
        }
        "3학년" -> {
            when(classInfo){
                "1반" -> {
                    viewModel.homeroom(3,1)
                }
                "2반" -> {
                    viewModel.homeroom(3,2)
                }
                "3반" -> {
                    viewModel.homeroom(3,3)
                }
                "4반" -> {
                    viewModel.homeroom(3,4)
                }
            }
        }
    }
}