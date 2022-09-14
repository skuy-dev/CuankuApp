package com.example.cuanku.screens.activity.targets.add

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.viewModels
import com.example.cuanku.base.BaseActivity
import com.example.cuanku.base.NetworkResult
import com.example.cuanku.databinding.ActivityAddTargetBinding
import com.example.cuanku.screens.activity.targets.TargetsViewModel
import com.github.dhaval2404.imagepicker.ImagePicker
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.*
import java.text.SimpleDateFormat
import java.util.*


@AndroidEntryPoint
class AddTargetActivity : BaseActivity<ActivityAddTargetBinding>() {

    private val viewModel: TargetsViewModel by viewModels()

    private lateinit var resultUri: Uri
    private lateinit var resultFile: File
    private lateinit var resultDate: String

    companion object {
        private const val GALLERY_IMAGE_REQ_CODE = 102
    }


    override fun setLayout(inflater: LayoutInflater): ActivityAddTargetBinding {
        return ActivityAddTargetBinding.inflate(inflater)
    }

    override fun initialization() {
        setonClickListener()
    }

    override fun observeViewModel() {
        viewModel.addTarget.observe(this) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    val data = response.data?.meta?.code
                    if (data == 200) {
                        Toast.makeText(
                            this,
                            "Data Berhasil Ditambahkan",
                            Toast.LENGTH_SHORT
                        ).show()
                        dismissLoading()
                    }
                }
                is NetworkResult.Loading -> {
                    this.showLoading()
                }
                is NetworkResult.Error -> {
                    dismissLoading()
                    response.message?.getContentIfNotHandled()?.let {
                        Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }


    }

    private fun checkFormAddTarget() {

        val mediaType = ("multipart/form-data").toMediaTypeOrNull()

        val requestImageFile = resultFile.asRequestBody("multipart/form-data".toMediaTypeOrNull())
        val imageMultipart: MultipartBody.Part = MultipartBody.Part.createFormData(
            "image_url",
            resultFile.name,
            requestImageFile
        )

        val name = binding.edtNameTarget.text.toString().toRequestBody(mediaType)
        val duration = resultDate.toRequestBody(mediaType)
        val remaining = binding.edtHarga.text.toString().toRequestBody(mediaType)
        val nominal = binding.edtHarga.value.toString().toRequestBody(mediaType)


        viewModel.addTarget(
            file = imageMultipart,
            name = name,
            duration = duration,
            remaining = remaining,
            nominal = nominal,
        )
    }

    @Suppress("UNUSED_PARAMETER")
    private fun pickGalleryImage() {
        ImagePicker.with(this)
            .compress(1024)
            .crop()
            .galleryOnly()
            .galleryMimeTypes(
                mimeTypes = arrayOf(
                    "image/png",
                    "image/jpg",
                    "image/jpeg"
                )
            )
            .maxResultSize(1080, 1080)
            .start(GALLERY_IMAGE_REQ_CODE)
    }

    private fun setonClickListener() {

        binding.btnImageTarget.setOnClickListener {
            pickGalleryImage()
        }

        binding.btnAddTarget.setOnClickListener {
            checkFormAddTarget()
        }

        binding.btnAddDate.setOnClickListener {
            showDatePickerDialog()
        }

    }

    private fun showDatePickerDialog() {
        val cal = Calendar.getInstance()
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this@AddTargetActivity, { _, _, _, _ ->
                val format = "yyyy-MM-dd"
                val sdf = SimpleDateFormat(format, Locale.getDefault())
                resultDate = sdf.format(cal.time)
                val sdfConvert = SimpleDateFormat("E, dd MMMM yyyy", Locale.getDefault())
                binding.edtDate.text = sdfConvert.format(cal.time)
            },
            year, month, day
        )

        datePickerDialog.datePicker.minDate = System.currentTimeMillis()
        datePickerDialog.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode) {
            Activity.RESULT_OK -> {
                val uri: Uri = data?.data!!
                resultUri = uri
                binding.btnImageTarget.setImageURI(resultUri)
                resultFile = File(resultUri.path)
            }
            ImagePicker.RESULT_ERROR -> {
                Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
            }
            else -> {
                Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show()
            }
        }
    }

//    fun reduceFileImage(file: File): File {
//        val bitmap = BitmapFactory.decodeFile(file.path)
//
//        var compressQuality = 1000
//        var streamLength: Int
//
//        do {
//            val bmpStream = ByteArrayOutputStream()
//            bitmap.compress(Bitmap.CompressFormat.PNG, compressQuality, bmpStream)
//            val bmpPictByteArray = bmpStream.toByteArray()
//            streamLength = bmpPictByteArray.size
//            compressQuality -= 5
//
//        } while (streamLength > 1000000)
//
//        bitmap.compress(Bitmap.CompressFormat.PNG, compressQuality, FileOutputStream(file))
//        return file
//    }

//    fun uriToFile(selectedImg: Uri): File {
//        val contentResolver: ContentResolver = this.contentResolver
//        val myFile = createTempFile()
//
//        val inputStream = contentResolver.openInputStream(selectedImg) as InputStream
//        val outputStream: OutputStream = FileOutputStream(myFile)
//        val buf = ByteArray(1024)
//        var len: Int
//        while (inputStream.read(buf).also {
//                len = it
//            } > 0)
//            outputStream.write(buf, 0, len)
//        outputStream.close()
//        inputStream.close()
//
//        return myFile
//    }
//
//    private fun createTempFile(): File {
//        val storageDir: File? = this.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
//        return File.createTempFile("images", ".png", storageDir)
//    }


}