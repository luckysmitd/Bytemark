package com.bytemark.permissions;

import java.util.ArrayList;

public interface PermissionResultCallback
{
 void permissionGranted(int requestCode);
 void partialPermissionGranted(int requestCode, ArrayList<String> grantedPermissions);
 void permissionDenied(int requestCode);
 void neverAskAgain(int requestCode);
}