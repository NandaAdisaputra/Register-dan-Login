package com.nandaadisaputra.medicinal_plants_marketplace.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.View
import com.nandaadisaputra.medicinal_plants_marketplace.R
import com.nandaadisaputra.medicinal_plants_marketplace.model.ResponseRegisterLogin
import com.nandaadisaputra.medicinal_plants_marketplace.network.InitRetrofit
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.progressDialog
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity(), View.OnClickListener {

    private var strlevel: String? = null
    private var strusername: String? = null
    private var strnomorhp : String? = null
    private var stremail: String? = null
    private var stralamat: String? = null
    private var strpassword: String? = null
    private var strconpassword: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        regAdmin.setOnClickListener(this)
        regUser.setOnClickListener(this)
        regBtnRegister.setOnClickListener(this)
        regBtnLog.setOnClickListener(this)
        if (regAdmin.isChecked){
            strlevel ="admin"
        }else{
            strlevel ="user"
        }
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.regAdmin -> strlevel = "admin"
            R.id.regUser -> strlevel = "user"
            R.id.regBtnLog -> startActivity<LoginActivity>()
            R.id.regBtnRegister -> register()
        }
    }

    private fun register() {
        strusername = regUsername.text.toString()
        strnomorhp = regNomorhp.text.toString()
        stremail = regEmail.text.toString()
        stralamat = regAlamat.text.toString()
        strpassword = regPass.text.toString()
        strconpassword = regConfPass.text.toString()
        if (TextUtils.isEmpty(strusername)){
            regUsername.error = "nama tidak boleh kosong"
            regUsername.requestFocus()
        } else if(TextUtils.isEmpty(strnomorhp)) {
            regNomorhp.error = "nomor hp tidak boleh kosong"
            regNomorhp.requestFocus()
        } else if(TextUtils.isEmpty(stremail)) {
            regEmail.error = "email tidak boleh kosong"
            regEmail.requestFocus()
        } else if(TextUtils.isEmpty(stralamat)) {
            regAlamat.error = "alamat tidak boleh kosong"
            regAlamat.requestFocus()
        } else if (TextUtils.isEmpty(strpassword)) {
            regPass.error = "password tidak boleh kosong"
            regPass.requestFocus()

        } else if (TextUtils.isEmpty(strconpassword)) {
            regConfPass.error = "confirm password tidak boleh kosong"
            regConfPass.requestFocus()
        } else if (strpassword!!.length < 6) {
            regPass.error = "password harus lebih dari 6 karakter"
            regPass.requestFocus()
        } else if (strpassword != strconpassword) {
            regConfPass.error = "password confirm tidak sama"
            regConfPass.requestFocus()
        } else {
            prosesregister()
        }}

    private fun prosesregister() {
        val dialog = progressDialog(
            message = "proses register user"
            , title = "loading . . . "
        )
        val api = InitRetrofit.getInstance()
        val responregis = api.registeruser(
            strusername.toString(), strnomorhp.toString(), stremail.toString(), stralamat.toString(), strpassword.toString(), strlevel.toString()
        )
        //panggil callback
        responregis.enqueue(object : Callback<ResponseRegisterLogin> {

            override fun onResponse(call: Call<ResponseRegisterLogin>, response: Response<ResponseRegisterLogin>) {
                val result = response.body()?.result
                val msg = response.body()?.msg
                //untuk menghilangkan dialog
                dialog.dismiss()
                if (result!!.equals(1)) {
                    toast(msg.toString())
                    startActivity<LoginActivity>()
                    finish()
                } else {
                    toast(msg.toString())
                }
            }

            override fun onFailure(call: Call<ResponseRegisterLogin>, t: Throwable) {
                dialog.dismiss()
                toast("gagal koneksi"+t.localizedMessage)
            }

        })

    }

}
