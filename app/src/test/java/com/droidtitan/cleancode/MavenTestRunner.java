package com.droidtitan.cleancode;

import org.junit.runners.model.InitializationError;
import org.robolectric.AndroidManifest;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.res.Fs;
import org.robolectric.res.FsFile;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MavenTestRunner extends RobolectricTestRunner {

    public MavenTestRunner(Class<?> testClass) throws InitializationError {
        super(testClass);
    }

    @Override
    protected AndroidManifest createAppManifest(FsFile manifestFile, FsFile resDir, FsFile assetsDir) {
        return new MavenAndroidManifest(Fs.newFile(new File("../app/src/main")));
    }

    /**
     * The class is extended to support maven test runs.
     * see: http://stackoverflow.com/questions/16907838/robolectric-2-x-maven-on-jenkins-failed-with-apklib-dependencies
     */
    public static class MavenAndroidManifest extends AndroidManifest {
        public MavenAndroidManifest(FsFile baseDir) {
            super(baseDir);
        }

        @Override
        protected List<FsFile> findLibraries() {
            // Try unpack folder from maven.
            FsFile unpack = getBaseDir().join("target/unpack/apklibs");
            if (unpack.exists()) {
                FsFile[] libs = unpack.listFiles();
                if (libs != null) {

                    return Arrays.asList(libs);
                }
            }
            return Collections.emptyList();
        }

        @Override
        protected AndroidManifest createLibraryAndroidManifest(FsFile libraryBaseDir) {
            return new MavenAndroidManifest(libraryBaseDir);
        }
    }

}

