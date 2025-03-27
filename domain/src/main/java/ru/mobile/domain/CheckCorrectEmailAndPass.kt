package ru.mobile.domain



    class CheckCorrectEmailAndPass(var email:String, var pass: String) {
        fun check(): Boolean{
            var c = mutableListOf<String>()
            email = email.lowercase()
            val alf ="a".."z"
            for (x in email){
                if (x.toString() !in alf){
                    c.add(x.toString())
                }
            }
            if (c == mutableListOf("@", ".") && pass.isNotEmpty()) return true
            else return false
        }
    }

