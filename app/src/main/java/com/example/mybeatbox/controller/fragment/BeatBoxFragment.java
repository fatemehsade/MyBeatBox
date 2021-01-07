package com.example.mybeatbox.controller.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mybeatbox.R;
import com.example.mybeatbox.model.Sound;
import com.example.mybeatbox.repository.SoundRepository;

import java.util.List;


public class BeatBoxFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private SoundRepository mRepository;



    public BeatBoxFragment() {
        // Required empty public constructor
    }


    public static BeatBoxFragment newInstance() {
        BeatBoxFragment fragment = new BeatBoxFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRepository=SoundRepository.getsInstance(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_beat_box, container, false);
        findViews(view);
        initViews();
        setUpAdaptor();
        return view;
    }

    private void initViews() {
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
    }

    private void findViews(View view) {
        mRecyclerView=view.findViewById(R.id.recycler_view_beat_box);
    }

    public class SoundAdaptor extends RecyclerView.Adapter<SoundHolder>{

        private List<Sound> mSounds;

        public List<Sound> getSounds() {
            return mSounds;
        }

        public void setSounds(List<Sound> sounds) {
            mSounds = sounds;
        }

        public SoundAdaptor(List<Sound> sounds) {
            mSounds = sounds;
        }

        @NonNull
        @Override
        public SoundHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view=LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item_sound,parent,false);
            return new SoundHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull SoundHolder holder, int position) {
            Sound sound=mSounds.get(position);
            holder.bind(sound);

        }

        @Override
        public int getItemCount() {
            return mSounds.size();
        }
    }

    private class SoundHolder extends RecyclerView.ViewHolder{
        private Button beatBoxButton;
        private Sound mSound;

        public SoundHolder(@NonNull View itemView) {
            super(itemView);
            beatBoxButton=itemView.findViewById(R.id.button_beat_box);
        }
        public void bind(Sound sound){
            mSound=sound;
            beatBoxButton.setText(sound.getName());
        }
    }


    private void setUpAdaptor(){
        List<Sound> sounds=mRepository.getSounds();
        SoundAdaptor adaptor=new SoundAdaptor(sounds);
        mRecyclerView.setAdapter(adaptor);

    }
}