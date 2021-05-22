package com.uncode.getfilepathlocalstorage

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.nbsp.materialfilepicker.MaterialFilePicker
import com.nbsp.materialfilepicker.ui.FilePickerActivity
import com.uncode.getfilepathlocalstorage.databinding.ActivityMainBinding
import es.dmoral.toasty.Toasty
import java.io.File
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding
    private val binding get() = mainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.openFileButton.setOnClickListener { setupPermissions() }
    }


    private fun getMeTheFileName() {
        val ex = Environment.DIRECTORY_DCIM
        val folder = File(ex, "")

        MaterialFilePicker().apply {
            withActivity(this@MainActivity)
            withCloseMenu(true)
            withPath(folder.absolutePath)
            withHiddenFiles(true)
            withFilter(Pattern.compile(".*\\.(.*)$"))
            withFilterDirectories(false)
            withTitle("File Manager")
            withRequestCode(FILE_PICKER_CODE)
            start()
        }
    }


/* onActivityResult is deprecated and is replaced with RegisterForActivityResult and Callbacks.
I will make a dedicated video on that too and will update this repository too */

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == FILE_PICKER_CODE && resultCode == RESULT_OK) {
            val path = data?.getStringExtra(FilePickerActivity.RESULT_FILE_PATH)
            binding.filePathTv.text = path
            if (path.isNullOrBlank())
                Toasty.error(applicationContext, "Oops! File path not found.")
            else
                Toasty.error(applicationContext, "Hurray! Got the file path.")
        }
    }

    private fun setupPermissions() {
        val permission = ContextCompat.checkSelfPermission(
            applicationContext,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
        if (permission != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )
            ) {
                val builder = MaterialAlertDialogBuilder(applicationContext)
                builder.setMessage("Permission to access the storage is required.")
                    .setTitle("Permission required")

                builder.setPositiveButton(
                    "OK"
                ) { _, _ ->
                    Toasty.normal(applicationContext, "Clicked").show()
                    makeRequest()
                }

                val dialog = builder.create()
                dialog.show()
            } else {
                makeRequest()
            }
        } else {
            getMeTheFileName()
        }
    }

    private fun makeRequest() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            PERMISSION_CODE
        )
    }

    companion object {
        val FILE_PICKER_CODE = 999
        val PERMISSION_CODE = 1008
    }
}