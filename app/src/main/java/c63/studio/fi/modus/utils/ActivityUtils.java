package c63.studio.fi.modus.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import static com.google.common.base.Preconditions.checkNotNull;

public class ActivityUtils {
    /**
     * The {@code fragment} is added to the container view with id {@code frameId}. The operation is
     * performed by the {@code fragmentManager}.
     */
    public static void addFragmentToActivity(@NonNull FragmentManager fragmentManager,
                                             @NonNull Fragment fragment, int frameId) {
        checkNotNull(fragmentManager);
        checkNotNull(fragment);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment);
        transaction.commit();
    }

    public static void hideKeyboard(Activity activity) {
        View view = activity.getCurrentFocus();
        hideKeyboardForView(activity, view);
    }

    public static void hideKeyboardForView(@NonNull Activity activity, @Nullable View view) {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static void showKeyboard(@NonNull Context context) {
        if (context instanceof AppCompatActivity) {
            showKeyboard((AppCompatActivity) context);
        }
    }

    public static void showKeyboard(@NonNull Activity activity) {
        showKeyboardForView(
                (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE),
                activity.getCurrentFocus()
        );
    }

    private static void showKeyboardForView(@NonNull InputMethodManager imm, @Nullable View view) {
        if (view != null) {
            imm.toggleSoftInputFromWindow(view.getWindowToken(), InputMethodManager.SHOW_IMPLICIT, 0);
        }
    }

    public static boolean checkCanHandleIntent(@NonNull Intent intent, @NonNull PackageManager packageManager) {
        return intent.resolveActivity(packageManager) != null;
    }

    public static boolean isKeyBoardShown(@NonNull Activity activity) {
        InputMethodManager imm =
                (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        return imm.isAcceptingText();
    }

}
