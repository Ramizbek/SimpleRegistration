package ramizbek.aliyev.yakuniy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ramizbek.aliyev.yakuniy.adapters.MyAdapter
import ramizbek.aliyev.yakuniy.databinding.FragmentAllBinding
import ramizbek.aliyev.yakuniy.db.MyDBHelper
import ramizbek.aliyev.yakuniy.db.User

class AllFragment : Fragment() {
    lateinit var binding: FragmentAllBinding
    lateinit var myDBHelper: MyDBHelper
    lateinit var myAdapter: MyAdapter
    lateinit var list: ArrayList<User>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAllBinding.inflate(layoutInflater)
        binding.apply {

            myDBHelper = MyDBHelper(root.context)
            list = myDBHelper.readUser()

            rv.adapter = myAdapter
            myAdapter = MyAdapter(list)

        }

        return binding.root
    }
}