package ru.demqn.appname

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ActorsAdapter(context: Context, var actors:List<Actor>) : RecyclerView.Adapter<ActorsViewHolder>(){

    private val inflater : LayoutInflater = LayoutInflater.from(context)

    fun getItem(position: Int) : Actor = actors[position]

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorsViewHolder {
        return ActorsViewHolder(inflater.inflate(R.layout.view_holder_actor, parent, false))
    }

    override fun onBindViewHolder(holder: ActorsViewHolder, position: Int) {
        holder.Bind(getItem(position))
    }

    override fun getItemCount(): Int = actors.size
}

class ActorsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    private val nameActor: TextView? = itemView.findViewById(R.id.actor_name_text_view)
    private val photoActor: ImageView? = itemView.findViewById(R.id.actor_photo_image_view)

    fun Bind(actor: Actor) {
        nameActor?.text = actor.nameActor
        Glide
            .with(itemView.context)
            .load(actor.photoActor)
            .override(80, 80)
//            .placeholder(R.drawable.chris_hemsworth)
            .into(photoActor!!)
    }
}