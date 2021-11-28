package com.example.mysocialnetwork.user

class UsersData {
    private var usersList: MutableList<User> = mutableListOf(
        User(
            0, "Jon", phPath1, "was online 1 hour ago", "Reading",
            "bkslvr1987@gmail.com", "Enjoy reading"
        ),
        User(
            1, "Bob", phPath2, "was online 12 minutes ago", "Swimming",
            "bbbbbob2133@gmail.com", "100m: 23 sec"
        ),
        User(
            2, "Ann", phPath3, "was online 10 hour ago", "Football",
            "goldnsdf@gmail.com", "Training for Gold Ball"
        ),
        User(
            3, "Ferdinand", phPath4, "was online 3 days ago", "Golf",
            "aaaaaaa@gmail.com", "qwerty"
        ),
        User(
            4, "David", phPath5, "was online yesterday ago", "Climbing",
            "b2342555@gmail.com", "Hills is my life"
        ),
        User(
            5, "Paul", phPath6, "online", "Ride a bike",
            "biker2001@gmail.com", "Broke leg yesterday"
        ),
        User(
            6, "Mike", phPath7, "was online a week ago", "PC games",
            "fdsfa@gmail.com", "Silent Hill lover"
        ),
        User(
            7, "Julia", phPath8, "was online 6 years ago", "Cooking",
            "qwerty@gmail.com", "You know how to boil a water?"
        ),
        User(
            8, "Kelly", phPath9, "was online 3 month ago", "Sleep",
            "zzzzzz@gmail.com", "Sleep, eat, repeat"
        )
    )

    companion object {
        const val phPath1 = "@drawable/ic_user1"
        const val phPath2 = "@drawable/ic_user2"
        const val phPath3 = "@drawable/ic_user3"
        const val phPath4 = "@drawable/ic_user4"
        const val phPath5 = "@drawable/ic_user5"
        const val phPath6 = "@drawable/ic_user6"
        const val phPath7 = "@drawable/ic_user7"
        const val phPath8 = "@drawable/ic_user8"
        const val phPath9 = "@drawable/ic_user9"
    }

    fun getUsersData(): List<User> {
        return usersList
    }

    fun addNewUser(user: User) {
        usersList.add(user)
    }
}