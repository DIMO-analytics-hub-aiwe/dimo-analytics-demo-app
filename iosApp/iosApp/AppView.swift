import SwiftUI
import Shared

struct AppView: UIViewControllerRepresentable {
    let appComponent: AppComponent
    let backDispatcher: BackDispatcher

    func makeUIViewController(context: Context) -> UIViewController {
        AppViewControllerKt.appViewController(appComponent: appComponent, backDispatcher: backDispatcher)
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

struct ContentView: View {

    let component: AppComponent
    let backDispatcher: BackDispatcher


    @Environment(\.colorScheme) var colorScheme

    var body: some View {
        let backgroundColor = Color(
            colorScheme == .dark
            ? ThemeHelpersKt.MD_THEME_DARK_BACKGROUND
            : ThemeHelpersKt.MD_THEME_LIGHT_BACKGROUND
        )
        ZStack {
            backgroundColor
                .edgesIgnoringSafeArea(.all)

            AppView(appComponent: component, backDispatcher: backDispatcher)
                .ignoresSafeArea(.all)
        }.ignoresSafeArea(.all)
    }
}
