package com.example.rightechiot

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.rightechiot.databinding.FragmentLoginBinding
import retrofit2.Call
import retrofit2.Response


class LoginFragment : Fragment() {
    lateinit var binding: FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root
        val login = binding.loginTEXT
        val password = binding.passwordTEXT
        val loginButton = binding.loginButton
        loginButton.setOnClickListener {
            if (login.text.toString().isNotEmpty() && password.text.toString().isNotEmpty()) {
                val obj =
                    RequestModel(login = login.text.toString(), password = password.text.toString())

                retrofit.requestLogin(obj).enqueue(
                    object : retrofit2.Callback<ResponseModel> {
                        override fun onResponse(
                            call: Call<ResponseModel>,
                            response: Response<ResponseModel>
                        ) {
                            if (response.body().toString().isEmpty()) {
                                Toast.makeText(
                                    requireContext(),
                                    "You are not authorized!",
                                    Toast.LENGTH_SHORT
                                ).show();
                            } else {
                                AuthService().saveToken(response.body()!!.token)
                                Toast.makeText(
                                    requireContext(),
                                    "Token is not empty!",
                                    Toast.LENGTH_SHORT
                                ).show();
                                router.newRootScreen(Screens.chooseServer())

                            }
                        }

                        override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                            Toast.makeText(requireContext(), "Failed!", Toast.LENGTH_SHORT).show();
                        }
                    }
                )
            } else {
//                Toast.makeText(this@LoginFragment,"Please enter your name/password",Toast.LENGTH_LONG).show()
            }
        }
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() = LoginFragment()
    }
}