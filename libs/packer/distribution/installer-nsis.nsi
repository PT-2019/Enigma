;--------------------------------
;Include Modern UI

  !include "MUI2.nsh"

  !define MUI_ICON "installer-icon.ico"

;--------------------------------
;Other DLLs

  !include "ZipDLL.nsh"
  !include "FileAssoc.nsh"

;--------------------------------
;General

  ;Name and file
  Name "GdxTexturePacker"
  OutFile "output/${FILENAME}.exe"

  ;Default installation folder
  InstallDir "$PROGRAMFILES\GdxTexturePacker"
  
  ;Get installation folder from registry if available
  InstallDirRegKey HKCU "Software\GdxTexturePacker" ""

  ;Request application privileges for Windows Vista
  RequestExecutionLevel admin

;--------------------------------
;Interface Settings

  !define MUI_ABORTWARNING

;--------------------------------
;Pages

  !insertmacro MUI_PAGE_LICENSE "license.txt"
  !insertmacro MUI_PAGE_COMPONENTS
  !insertmacro MUI_PAGE_DIRECTORY
  !insertmacro MUI_PAGE_INSTFILES
  
  !insertmacro MUI_UNPAGE_CONFIRM
  !insertmacro MUI_UNPAGE_INSTFILES
  
;--------------------------------
;Languages
 
  !insertmacro MUI_LANGUAGE "English"

;--------------------------------
;Installer Sections

Section "GdxTexturePacker" AppInstall
SectionIn RO

  SetOutPath "$INSTDIR"

  File "output\${FILENAME}.zip"
  ZipDLL::extractall "$INSTDIR\${FILENAME}.zip" "$INSTDIR"
  Delete "$INSTDIR\${FILENAME}.zip"
  
  ;Store installation folder
  WriteRegStr HKCU "Software\GdxTexturePacker" "" $INSTDIR
  
  ;Create uninstaller
  WriteUninstaller "$INSTDIR\Uninstall.exe"

  ;Create files association (http://nsis.sourceforge.net/FileAssoc)
  !insertmacro APP_ASSOCIATE "tpproj" "GdxTexturePacker.Project" "GDX Texture Packer project" "$INSTDIR\icon.ico,0" "Open with GDX Texture Packer" "$INSTDIR\launcher.bat $\"%1$\""

  ;Delete legacy symlink files
  IfFileExists "$SMPROGRAMS\GdxTexturePacker.lnk" DeleteOldSymlinkPrograms
  DeleteOldSymlinkPrograms:
    Delete "$SMPROGRAMS\GdxTexturePacker.lnk"
  IfFileExists "$DESKTOP\GdxTexturePacker.lnk" DeleteOldSymlinkDesktop
  DeleteOldSymlinkDesktop:
    Delete "$DESKTOP\GdxTexturePacker.lnk"

SectionEnd

Section "Start Menu shortcuts" StartMenuShortcuts

    createShortCut "$SMPROGRAMS\GDX Texture Packer.lnk" "$INSTDIR\launcher_no_cmd.vbs" "" "$INSTDIR\icon.ico" 0

# default sec end
SectionEnd

Section "Desktop shortcuts" DesktopShortcuts
 
    createShortCut "$DESKTOP\GDX Texture Packer.lnk" "$INSTDIR\launcher_no_cmd.vbs" "" "$INSTDIR\icon.ico" 0
 
# default sec end
SectionEnd

;--------------------------------
;Uninstaller Section

Section "Uninstall"

  RMDir /r "$INSTDIR"
  RMDir /r "$PROFILE\.gdxtexturepackergui"

  ;Legacy link files
  Delete "$SMPROGRAMS\GdxTexturePacker.lnk"
  Delete "$DESKTOP\GdxTexturePacker.lnk"
  ;Actual link files
  Delete "$SMPROGRAMS\GDX Texture Packer.lnk"
  Delete "$DESKTOP\GDX Texture Packer.lnk"

  DeleteRegKey /ifempty HKCU "Software\GdxTexturePacker"

  ;Unregister file extension
  !insertmacro APP_UNASSOCIATE "tpproj" "GdxTexturePacker.Project"

SectionEnd

;--------------------------------
;Descriptions

  ;Language strings
  LangString DESC_AppInstall ${LANG_ENGLISH} "Installs the GdxTexturePacker application."
  LangString DESC_StartMenuShortcuts ${LANG_ENGLISH} "Places a GdxTexturePacker folder containing shortcuts in the start menu."
  LangString DESC_DesktopShortcuts ${LANG_ENGLISH} "Places a GdxTexturePacker shortcut on the desktop."

  ;Assign language strings to sections
  !insertmacro MUI_FUNCTION_DESCRIPTION_BEGIN
    !insertmacro MUI_DESCRIPTION_TEXT ${AppInstall} $(DESC_AppInstall)
    !insertmacro MUI_DESCRIPTION_TEXT ${StartMenuShortcuts} $(DESC_StartMenuShortcuts)
    !insertmacro MUI_DESCRIPTION_TEXT ${DesktopShortcuts} $(DESC_DesktopShortcuts)
  !insertmacro MUI_FUNCTION_DESCRIPTION_END