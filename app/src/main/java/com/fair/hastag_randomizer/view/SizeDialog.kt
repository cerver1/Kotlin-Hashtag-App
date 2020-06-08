package com.fair.hastag_randomizer.view

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.fair.hastag_randomizer.R
import com.fair.hastag_randomizer.repository.toast

class SizeDialog(private val context: Context) {


    fun size(){

        val mDialogView = LayoutInflater.from(context).inflate(R.layout.dialog_size, null)
        val mBuilder = AlertDialog.Builder(context)
            .setView(mDialogView)
            .setTitle("Login Form")
        val  mAlertDialog = mBuilder.show()

    }

    // Method to show an alert dialog with single choice list items
    fun showSizeDialog(){
        // Late initialize an alert dialog object
        lateinit var dialog:AlertDialog

        val array = arrayOf("Small","Medium","Full","Random")

        // Initialize a new instance of alert dialog builder object
        val builder = AlertDialog.Builder(context)

        // Set a title for alert dialog
        builder.setTitle("Choose an output size")

        // Set the single choice items for alert dialog with initial selection
        builder.setSingleChoiceItems(array,-1) { _, which->
            // Get the dialog selected item
            val size = array[which]

            // Try to parse user selected color string
            try {
                // Change the layout background color using user selection
                context.toast(size)
            }catch (e:IllegalArgumentException){
                // Catch the color string parse exception
                context.toast("Something went wrong")
            }

            // Dismiss the dialog
            dialog.dismiss()
        }


        // Initialize the AlertDialog using builder object
        dialog = builder.create()

        // Finally, display the alert dialog
        dialog.show()
    }
}
