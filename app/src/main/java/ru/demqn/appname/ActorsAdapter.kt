package ru.demqn.appname

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.demqn.appname.data.Actor

class ActorsAdapter(var actors: List<ru.demqn.appname.data.Actor>) : RecyclerView.Adapter<ActorsViewHolder>() {

    fun getItem(position: Int): ru.demqn.appname.data.Actor = actors[position]

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorsViewHolder {
        return ActorsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_holder_actor, parent, false))
    }

    override fun onBindViewHolder(holder: ActorsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount(): Int = actors.size
}

class ActorsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val nameActor: TextView = itemView.findViewById(R.id.actor_name_text_view)
    private val photoActor: ImageView = itemView.findViewById(R.id.actor_photo_image_view)

    fun bind(actor: Actor) {
        nameActor.text = actor.name
        Glide
                .with(itemView.context)
                .load(actor.picture)
                .into(photoActor)
    }
}