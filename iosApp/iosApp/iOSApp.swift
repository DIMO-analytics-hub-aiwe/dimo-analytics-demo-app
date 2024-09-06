import SwiftUI
import Shared

@main
struct iOSApp: App {

    init() {
        KoinHelpersKt.iosDinsuranceApp()
    }

    @UIApplicationDelegateAdaptor(AppDelegate.self)
    var appDelegate: AppDelegate

    private var appInitParamsHolder: AppInitParamsHolder { appDelegate.getAppInitParamsHolder() }

    var body: some Scene {
        WindowGroup {
            AppView(appComponent: appInitParamsHolder.appComponent, backDispatcher: appInitParamsHolder.backDispatcher)
                .ignoresSafeArea(.all)
                .onReceive(NotificationCenter.default.publisher(for: UIApplication.didBecomeActiveNotification)) { _ in
                    LifecycleRegistryExtKt.resume(appInitParamsHolder.lifecycle)
                }
                .onReceive(NotificationCenter.default.publisher(for: UIApplication.willResignActiveNotification)) { _ in
                    LifecycleRegistryExtKt.pause(appInitParamsHolder.lifecycle)
                }
                .onReceive(NotificationCenter.default.publisher(for: UIApplication.didEnterBackgroundNotification)) { _ in
                    LifecycleRegistryExtKt.stop(appInitParamsHolder.lifecycle)
                }
                .onReceive(NotificationCenter.default.publisher(for: UIApplication.willTerminateNotification)) { _ in
                    LifecycleRegistryExtKt.destroy(appInitParamsHolder.lifecycle)
                }
        }
    }
}

class AppDelegate: NSObject, UIApplicationDelegate {

    private var appInitParamsHolder: AppInitParamsHolder?

    func application(_ application: UIApplication, shouldSaveSecureApplicationState coder: NSCoder) -> Bool {
        StateKeeperUtilsKt.save(coder: coder, state: appInitParamsHolder!.stateKeeper.save())
        return true
    }

    func application(_ application: UIApplication, shouldRestoreSecureApplicationState coder: NSCoder) -> Bool {
        let savedState = StateKeeperUtilsKt.restore(coder: coder)
        appInitParamsHolder = AppInitParamsHolder(savedState: savedState)
        return true
    }

    func application(_ app: UIApplication, open url: URL, options: [UIApplication.OpenURLOptionsKey : Any] = [:]) -> Bool {
            let code = URLComponents(url: url, resolvingAgainstBaseURL: false)?
                    .queryItems?.first(where: { $0.name == "code" })?.value

                OAuthCallbackHandler().handleAuthCode(code: code) { error in
                    if let error = error {
                        print("Error: \(error.localizedDescription)")
                    }
                }
            return true
        }

    fileprivate func getAppInitParamsHolder() -> AppInitParamsHolder {
        if (appInitParamsHolder == nil) {
            appInitParamsHolder = AppInitParamsHolder(savedState: nil)
        }
        return appInitParamsHolder!
    }
}

private class AppInitParamsHolder {
    let lifecycle: LifecycleRegistry
    let backDispatcher: BackDispatcher
    let stateKeeper: StateKeeperDispatcher
    let appComponent: AppComponent

    init(savedState: SerializableContainer?) {
        lifecycle = LifecycleRegistryKt.LifecycleRegistry()
        stateKeeper = StateKeeperDispatcherKt.StateKeeperDispatcher(savedState: savedState)
        backDispatcher = BackDispatcherKt.BackDispatcher()

        appComponent = AppComponentImpl(
            componentContext: DefaultComponentContext(
                lifecycle: ApplicationLifecycle(),
                stateKeeper: stateKeeper,
                instanceKeeper: nil,
                backHandler: backDispatcher
            )
        )

        LifecycleRegistryExtKt.create(lifecycle)
    }
}
