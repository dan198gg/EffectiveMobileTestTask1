package ru.mobile.domain



    class CheckCorrectEmailAndPass(var email:String, var pass: String) {
        fun check(): Boolean{
            var c = mutableListOf<String>()
            email = email.lowercase()
            val alf ="a".."z"
            for (x in email){
                if (x.toString() !in alf && x.toString() !in "0".."9"){
                    c.add(x.toString())
                }
            }
            if (c == mutableListOf("@", ".") && pass.isNotEmpty() && ((email.indexOf('@')+1)!=email.indexOf('.')) && email[0]!='@' && email.indexOf('.')!= email.lastIndex) {
                return true
            } else return false
        }
    }

