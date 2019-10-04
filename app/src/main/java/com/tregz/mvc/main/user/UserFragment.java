package com.tregz.mvc.main.user;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.tregz.mvc.R;
import com.tregz.mvc.arch.user.UserShared;
import com.tregz.mvc.base.BaseFragment;
import com.tregz.mvc.core.date.DateUtil;
import com.tregz.mvc.data.user.UserModel;
import com.tregz.mvc.main.MainFragment;

public class UserFragment extends BaseFragment {

    private UserModel user;
    private UserShared preferences;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getContext() != null && preferences == null) preferences = new UserShared(getContext());

        // Is fragment restored?
        Log.d(TAG, "SavedInstance: " + (savedInstanceState != null));
        if (savedInstanceState != null) {
            user = savedInstanceState.getParcelable(UserModel.TAG);
            if (user != null && user.getEmail() != null) Log.d(TAG, user.getEmail());
        } else user = new UserModel();
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        Log.d(TAG, MainFragment.State.VIEW_INFLATING.name());
        return inflater.inflate(R.layout.fragment_auth, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EditText emailEditor = view.findViewById(R.id.email_editor);
        emailEditor.setOnFocusChangeListener(emailListener);
        if (preferences != null) emailEditor.setText(preferences.getEmail());
        view.findViewById(R.id.first_name_editor).setOnFocusChangeListener(firstNameListener);
    }

    @Override
    public void onStart() {
        super.onStart();
        listener.onFragmentStart("Auth");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(UserModel.TAG, user);
    }

    private View.OnFocusChangeListener emailListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View view, boolean b) {
            String sequence = ((EditText)view).getText().toString();
            if (!sequence.isEmpty()) {
                if (UserUtil.isEmailValid(sequence)) {
                    user.setEmail(sequence);
                    if (preferences != null) preferences.setEmail(sequence);
                }
                else ((EditText)view).setError("Email is not valid");
            }
        }
    };

    private View.OnFocusChangeListener firstNameListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View view, boolean b) {
            String sequence = ((EditText)view).getText().toString();
            if (!sequence.isEmpty()) {
                if (UserUtil.isPersonNameValid(sequence)) user.setFirstName(sequence);
                else ((EditText)view).setError("First name is not valid");
            }
        }
    };

    static {
        TAG = UserFragment.class.getSimpleName();
    }
}
