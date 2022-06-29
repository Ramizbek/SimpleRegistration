package ramizbek.aliyev.yakuniy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import ramizbek.aliyev.yakuniy.databinding.FragmentHomeBinding
import ramizbek.aliyev.yakuniy.db.MyDBHelper

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    lateinit var myDBHelper: MyDBHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        binding.apply {
            myDBHelper = MyDBHelper(root.context)
            btnSignUp.setOnClickListener {
                findNavController().navigate(R.id.signupFragment)
            }
            btnSignIn.setOnClickListener {
                if (addUsername.text.toString().isNotEmpty() && myPassword.text.toString()
                        .isNotEmpty()
                ) {
                    for (i in myDBHelper.readUser()) {
                        if (i.userName == addUsername.text.toString() && i.password == myPassword.text.toString()) {
                            findNavController().navigate(R.id.allFragment)
                        } else {
                            Toast.makeText(root.context, "Malumot yetatli emas", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            }
        }
        return binding.root
    }
}