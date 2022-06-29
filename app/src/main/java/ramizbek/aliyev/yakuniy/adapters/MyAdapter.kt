package ramizbek.aliyev.yakuniy.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ramizbek.aliyev.yakuniy.databinding.ItemRvBinding
import ramizbek.aliyev.yakuniy.db.User


class MyAdapter(
    private var list: ArrayList<User>
) :
    RecyclerView.Adapter<MyAdapter.ViewHolderUser>() {

    inner class ViewHolderUser(private val binding: ItemRvBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(user: User, position: Int) {
            binding.apply {
                imageProfile.setImageURI(Uri.parse(user.image))
                userName.text = user.userName
                password.text = user.password
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderUser =
        ViewHolderUser(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(hol: ViewHolderUser, pos: Int) = hol.onBind(list[pos], pos)
    override fun getItemCount(): Int = list.size
}