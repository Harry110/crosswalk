// Copyright (c) 2013 Intel Corporation. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

package org.xwalk.core.internal.xwview.test;

import org.xwalk.core.internal.extensions.XWalkExtensionAndroid;

public class ExtensionEchoInternal extends XWalkExtensionAndroid {

    public ExtensionEchoInternal() {
        super("echo",
              "var echoListener = null;"
              + "extension.setMessageListener(function(msg) {"
              + "  if (echoListener instanceof Function) {"
              + "    echoListener(msg);"
              + "  };"
              + "});"
              + "exports.echo = function(msg, callback) {"
              + "  echoListener = callback;"
              + "  extension.postMessage(msg);"
              + "};"
              + "exports.echoSync = function(msg) {"
              + "  return extension.internal.sendSyncMessage(msg);"
              + "};"
             );
    }

    public void handleMessage(int instanceID, String message) {
        postMessage(instanceID, "From java:" + message);
    }

    public String handleSyncMessage(int instanceID, String message) {
        return "From java sync:" + message;
    }

    public void onDestroy() {
    }
}
