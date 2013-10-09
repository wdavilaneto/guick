package org.wdn.guick.util

/**
 * Created with IntelliJ IDEA.
 * User: walter
 * Date: 8/28/13
 * Time: 12:39 AM
 * To change this template use File | Settings | File Templates.
 */
class GuickExtendedClassLoader extends URLClassLoader {

    public GuickExtendedClassLoader(URLClassLoader classLoader) {
        super(classLoader.getURLs());
    }

    @Override
    public void addURL(URL url) {
        super.addURL(url);
    }

}
