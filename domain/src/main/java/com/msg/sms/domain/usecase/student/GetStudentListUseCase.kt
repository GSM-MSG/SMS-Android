package com.msg.sms.domain.usecase.student

import com.msg.sms.domain.repository.StudentRepository
import javax.inject.Inject

class GetStudentListUseCase @Inject constructor(
    private val repository: StudentRepository
) {
    suspend operator fun invoke(
        page: Int,
        size: Int,
        majors: List<String>?,
        techStacks: List<String>?,
        grade: List<Int>?,
        classNum: List<Int>?,
        department: List<String>?,
        stuNumSort: String?,
        formOfEmployment: List<String>?,
        minGsmAuthenticationScore: Int?,
        maxGsmAuthenticationScore: Int?,
        minSalary: Int?,
        maxSalary: Int?,
        gsmAuthenticationScoreSort: String?,
        salarySort: String?
    ) = kotlin.runCatching {
        repository.getStudentList(
            page = page,
            size = size,
            majors = majors,
            techStacks = techStacks,
            grade = grade,
            classNum = classNum,
            department = department,
            stuNumSort = stuNumSort,
            formOfEmployment = formOfEmployment,
            minGsmAuthenticationScore = minGsmAuthenticationScore,
            maxGsmAuthenticationScore = maxGsmAuthenticationScore,
            minSalary = minSalary,
            maxSalary = maxSalary,
            gsmAuthenticationScoreSort = gsmAuthenticationScoreSort,
            salarySort = salarySort
        )
    }
}