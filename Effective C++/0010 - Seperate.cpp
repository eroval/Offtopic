// header “webbrowser.h” — header for class WebBrowser itself
// as well as “core” WebBrowser-related functionality
namespace WebBrowserStuff {
class WebBrowser { ... };
... // “core” related functionality, e.g.
// non-member functions almost
// all clients need
}
// header “webbrowserbookmarks.h”
namespace WebBrowserStuff {
... // bookmark-related convenience
} // functions
// header “webbrowsercookies.h”
namespace WebBrowserStuff {
... // cookie-related convenience
} // functions
...