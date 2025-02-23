import * as React from "react";
import { WebView } from "react-native-webview";
import { StyleSheet, Alert, BackHandler } from "react-native";
import Constants from "expo-constants";

interface navType {
  url: string;
  canGoBack: boolean;
}

export default function App() {
  const webViewUrl = "http://192.168.0.49:5173/";
  const webViewRef = React.useRef<WebView>(null);
  const [navState, setNavState] = React.useState({ url: "", canGoBack: false });

  const close = () => {
    Alert.alert("종료하시겠어요?", "확인을 누르면 종료합니다.", [
      {
        text: "취소",
        onPress: () => {},
        style: "cancel",
      },
      {
        text: "확인",
        onPress: () => BackHandler.exitApp(),
      },
    ]);
  };

  React.useEffect(() => {
    const handleBackButton = () => {
      if (navState.canGoBack) {
        if (navState.url === webViewUrl) close();
        else webViewRef.current?.goBack();
      } else {
        close();
      }
      return true;
    };

    BackHandler.addEventListener("hardwareBackPress", handleBackButton);
    return () => {
      BackHandler.removeEventListener("hardwareBackPress", handleBackButton);
    };
  }, [navState]);

  return (
    <>
      <WebView
        ref={webViewRef}
        style={styles.container}
        source={{ uri: webViewUrl }}
        onNavigationStateChange={(nav: navType) => {
          setNavState({ url: nav.url, canGoBack: nav.canGoBack });
        }}
      />
    </>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    marginTop: Constants.statusBarHeight,
  },
});
