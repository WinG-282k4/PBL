<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <link
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css"
      rel="stylesheet"
    />
    <link rel="stylesheet" href="index2.css" />
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
    <script src="index3.js"></script>
    <script src="index2.js"></script>
    <style>
      iframe {
        width: 100%;
        height: 100%;
        border: none;
      }
    </style>
  </head>
  <body>
    <aside class="sidebar" style="max-width: 175px">
      <div class="sidebar-header" style="position: relative">
        <a
          class="logo"
          href="components/dashboard/dashboad.html"
          target="main-content"
          ><div class="zabbix-logo-sidebar"></div>
          <div class="zabbix-logo-sidebar-compact"></div
        ></a>
        <div class="sidebar-header-buttons">
          <button
            type="button"
            class="btn-icon zi-chevron-double-left js-sidebar-mode button-compact"
            title="Collapse sidebar"
          ></button
          ><button
            type="button"
            class="btn-icon zi-chevron-double-right js-sidebar-mode button-expand"
            title="Expand sidebar"
          ></button
          ><button
            type="button"
            class="btn-icon zi-collapse js-sidebar-mode button-hide"
            title="Hide sidebar"
          ></button
          ><button
            type="button"
            class="btn-icon zi-expand js-sidebar-mode button-show"
            title="Show sidebar"
          ></button>
        </div>
      </div>
      <div class="server-name" style="width: auto; max-width: 175px">
        myubuntu
      </div>
      <div class="sidebar-nav scrollable" tabindex="-1">
        <nav class="nav-main" role="navigation" aria-label="Main navigation">
          <ul class="menu-main" style="position: static">
            <li id="dashboard" class="is-selected">
              <a
                class="zi-dashboards"
                href="components/dashboard/dashboad.html"
                target="main-content"
                >Dashboards</a
              >
            </li>
            <li id="view" class="has-submenu is-expanded">
              <a class="zi-monitoring" href="#">Monitoring</a>
              <ul class="submenu" style="max-height: 153px">
                <li>
                  <a
                    href="check?action=problem"
                    target="main-content"
                    >Problems</a
                  >
                </li>
                <li>
                  <a
                    href="check?action=listhost"
                    target="main-content"
                    >Hosts</a
                  >
                </li>
                <li>
                  <a
                    href="check?action=latestdata"
                    target="main-content"
                    >Latest data</a
                  >
                </li>
              </ul>
            </li>
            <li id="config" class="has-submenu is-expanded">
              <a class="zi-data-collection" href="#">Data collection</a>
              <ul class="submenu" style="max-height: 214px">
                <li>
                  <a
                    href="check?action=listgrouphost"
                    target="main-content"
                    >Host groups</a
                  >
                </li>
                <li>
                  <a
                    href="check?action=listhost"
                    target="main-content"
                    >Hosts</a
                  >
                </li>
              </ul>
            </li>
          </ul>
        </nav>
      </div>
    </aside>
    <iframe src="components/dashboard/dashboad.html" name="main-content">
    </iframe>
  </body>
</html>
