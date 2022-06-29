package ramizbek.aliyev.yakuniy

import android.icu.text.SimpleDateFormat
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController

import androidx.activity.result.contract.ActivityResultContracts
import ramizbek.aliyev.yakuniy.databinding.FragmentSignupBinding
import ramizbek.aliyev.yakuniy.db.MyDBHelper
import ramizbek.aliyev.yakuniy.db.User
import java.io.File
import java.io.FileOutputStream
import java.util.*

class SignupFragment : Fragment() {

    lateinit var binding: FragmentSignupBinding
    lateinit var myDBHelper: MyDBHelper
    var imagePath: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignupBinding.inflate(layoutInflater)
        binding.apply {
            myDBHelper = MyDBHelper(root.context)
            btnAddImage.setOnClickListener { getImageContent.launch("image/*") }
            btnSignUpAdd.setOnClickListener {

                val n = addUsernameAdd.text.toString()
                val p = myPasswordAddAdd.text.toString()
                if (imagePath != null && n.isNotEmpty() && p.isNotEmpty()) {
                    myDBHelper.createUser(User(imagePath, n, p))
                    findNavController().popBackStack()
                    findNavController().navigate(R.id.allFragment)
                } else Toast.makeText(root.context, "Empty", Toast.LENGTH_SHORT).show()
            }
        }
        return binding.root

    }

    private val getImageContent =
        registerForActivityResult(ActivityResultContracts.GetContent()) {
            it ?: return@registerForActivityResult
            binding.imageHome.setImageURI(it)
            val imageName = SimpleDateFormat("ddMMyyyy_hh:mm:ss").format(Date())
            val file = File(requireActivity().filesDir, "$imageName.jpg")
            val inputStream = requireActivity().contentResolver?.openInputStream(it)
            val fileOutputStream = FileOutputStream(file)
            inputStream?.copyTo(fileOutputStream)
            inputStream?.close()
            fileOutputStream.close()
            imagePath = file.absolutePath
        }
}