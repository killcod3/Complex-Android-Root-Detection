import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RootDetector {

    public static boolean isDeviceRooted() {
        return checkBuildTags() || checkSuperUserPaths() || checkRootManagementApps() || checkSuBinary();
    }

    private static boolean checkBuildTags() {
        return "test-keys".equals(android.os.Build.TAGS);
    }

    private static boolean checkSuperUserPaths() {
        List<String> paths = Arrays.asList(
                "/system/app/Superuser.apk",
                "/sbin/su",
                "/system/bin/su",
                "/system/xbin/su",
                "/data/local/xbin/su",
                "/data/local/bin/su",
                "/system/sd/xbin/su",
                "/system/bin/failsafe/su",
                "/data/local/su"
        );

        for (String path : paths) {
            if (new File(path).exists()) {
                return true;
            }
        }

        return false;
    }

    private static boolean checkRootManagementApps() {
        List<String> rootManagementApps = Arrays.asList(
                "com.noshufou.android.su",
                "com.thirdparty.superuser",
                "eu.chainfire.supersu",
                "com.koushikdutta.superuser",
                "com.zachspong.temprootremovejb",
                "com.ramdroid.appquarantine"
        );

        List<String> installedPackages = new ArrayList<>();
        PackageManager packageManager = getPackageManager();
        List<ApplicationInfo> apps = packageManager.getInstalledApplications(PackageManager.GET_META_DATA);
        for (ApplicationInfo app : apps) {
            installedPackages.add(app.packageName);
        }

        for (String app : rootManagementApps) {
            if (installedPackages.contains(app)) {
                return true;
            }
        }

        return false;
    }

    private static boolean checkSuBinary() {
        String[] commands = {"/system/xbin/which su", "/system/bin/which su", "which su"};
        try {
            for (String command : commands) {
                Process process = Runtime.getRuntime().exec(command);
                int exitValue = process.waitFor();
                if (exitValue == 0) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}