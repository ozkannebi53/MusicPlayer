package com.ozkannebi53.musicplayer.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ozkannebi53.musicplayer.databinding.ItemMusicBinding
import com.ozkannebi53.musicplayer.models.Song

class MusicAdapter(
    private val context: Context,
    private val songs: List<Song>,
    private val onPlayClick: (Song) -> Unit,
    private val onDownloadClick: (Song) -> Unit,
    private val onMusicClick: (Song) -> Unit
) : RecyclerView.Adapter<MusicAdapter.MusicViewHolder>() {

    inner class MusicViewHolder(private val binding: ItemMusicBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(song: Song) {
            binding.apply {
                musicTitle.text = song.title
                musicArtist.text = song.artist
                musicDuration.text = formatDuration(song.duration)

                btnPlay.setOnClickListener {
                    onPlayClick(song)
                }

                btnDownload.setOnClickListener {
                    onDownloadClick(song)
                }

                btnMusic.setOnClickListener {
                    onMusicClick(song)
                }
            }
        }

        private fun formatDuration(duration: Long): String {
            val minutes = (duration / 1000) / 60
            val seconds = (duration / 1000) % 60
            return String.format("%d:%02d", minutes, seconds)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicViewHolder {
        val binding = ItemMusicBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MusicViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
        holder.bind(songs[position])
    }

    override fun getItemCount(): Int = songs.size
}
