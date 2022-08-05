package com.mega.programthree.viewmodel

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Context
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.i18n.phonenumbers.NumberParseException
import com.google.i18n.phonenumbers.PhoneNumberUtil
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber
import com.mega.programthree.R
import com.mega.programthree.view.fragment.SecondFragment
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern


class StarBucksViewModel:ViewModel() {

    private var context: Context?=null
    private var activityContext: Context?=null


    var firstName = MutableLiveData<String>()
    var lastName = MutableLiveData<String>()
    var birthDate = MutableLiveData<String>()
    var mobileNo = MutableLiveData<String>()
    var emailId = MutableLiveData<String>()

    val myCalendar = Calendar.getInstance()


    fun setContext(context: Context){
        this.context=context
    }
    fun setActivityContext(context: Context){
        this.activityContext=context
    }


    fun datePicker(){


        val date =
            OnDateSetListener { view, year, month, day ->
                myCalendar[Calendar.YEAR] = year
                myCalendar[Calendar.MONTH] = month
                myCalendar[Calendar.DAY_OF_MONTH] = day

            }


        val dialog = DatePickerDialog(context!!, date, 2000, 1, 1)
        dialog.show()

        val myFormat = "MM/dd/yyyy"
        val dateFormat = SimpleDateFormat(myFormat, Locale.US)
        birthDate.value=(dateFormat.format(myCalendar.time))





    }



    fun checkData(){
        if(strLength()){


        }
        else{

            val bundle=Bundle()

            val secondFragment = SecondFragment()

            bundle.putString("firstName", firstName.value)
            bundle.putString("lastName",lastName.value)
            bundle.putString("birthDate",birthDate.value)
            bundle.putString("mobileNo",mobileNo.value)
            bundle.putString("emailId",emailId.value)

           secondFragment.arguments=bundle

            (context as AppCompatActivity).supportFragmentManager .beginTransaction()
                .replace(R.id.layout_1,secondFragment)
                .commitNow()


        }

    }
    private fun strLength():Boolean{
        if(firstName.value==null){
            Toast.makeText(context, "Please put your name", Toast.LENGTH_SHORT).show()
            return true
        }
        else if(lastName.value==null){
            Toast.makeText(context, "Please put your last name", Toast.LENGTH_SHORT).show()
            return true

        }
        else if(birthDate.value==null){
            Toast.makeText(context, "Please put your date of birth", Toast.LENGTH_SHORT).show()
            return true
        }
        else if(mobileNo.value==null || !isValidMobile(mobileNo.value!!)  || !isPhoneNumberValid(mobileNo.value!!)){
            Toast.makeText(context, "Please put your mobile no", Toast.LENGTH_SHORT).show()
            return true
        }
        else if(emailId.value==null  || !isValidMail(emailId.value!!)){
            Toast.makeText(context, "Please put your email-Id", Toast.LENGTH_SHORT).show()
            return true
        }
        else{
            return false
        }
    }
    private fun isValidMail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isValidMobile(phone: String): Boolean {
        return if (!Pattern.matches("[a-zA-Z]+", phone)) {
            phone.length > 6 && phone.length <= 13
        } else false
    }

    fun isPhoneNumberValid(phone: String): Boolean {
        // creating an instance of PhoneNumber Utility class
        val phoneUtil = PhoneNumberUtil.getInstance()

        // creating a variable of type PhoneNumber
        var phoneNumber: PhoneNumber? = null
        try {
            // the parse method parses the string and
            // returns a PhoneNumber in the format of
            // specified region
            phoneNumber = phoneUtil.parse(phone, "IN")



        } catch (e: NumberParseException) {

            // if the phoneUtil is unable to parse any phone
            // number an exception occurs and gets caught in
            // this block

            e.printStackTrace()
        }

        // return the boolean value of the validation
        // performed
        return phoneUtil.isValidNumber(phoneNumber)
    }



}