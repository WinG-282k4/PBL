<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
    <link rel="stylesheet" href="CSS/index2.css" />
  </head>
  <body>
    <div class="wrapper">
      <header class="header-title">
        <nav
          class="sidebar-nav-toggle"
          role="navigation"
          aria-label="Sidebar control"
        >
          <button
            type="button"
            class="btn-icon zi-menu"
            title="Show sidebar"
            id="sidebar-button-toggle"
          ></button>
        </nav>
        <div><h1 id="page-title-general">Host groups</h1></div>
        <div class="header-controls">
          <nav aria-label="Content controls">
            <ul>
              <li>
                <a href = "">
                <button type="button" class="js-create-hostgroup">
                   Create host group 
                </button>
                </a>
              </li>
            </ul>
          </nav>
        </div>
      </header>
      <main>
        <div
          data-accessible="1"
          class="filter-space ui-tabs ui-corner-all ui-widget ui-widget-content ui-tabs-collapsible"
          id="filter_674c6e0c401ae"
          data-profile-idx="web.hostgroups.filter"
          data-profile-idx2="0"
          style=""
          aria-label="Filter"
        >
          <ul
            class="filter-btn-container ui-tabs-nav ui-corner-all ui-helper-reset ui-helper-clearfix ui-widget-header"
            role="tablist"
          ></ul>
          <form
            method="get"
            action="zabbix.php"
            accept-charset="utf-8"
            name="zbx_filter"
          >
            <input
              type="hidden"
              id="action"
              name="action"
              value="hostgroup.list"
            />
            <div
              class="filter-container ui-tabs-panel ui-corner-bottom ui-widget-content"
              id="tab_0"
              aria-labelledby="ui-id-1"
              role="tabpanel"
              aria-hidden="false"
            >
              <div class="table filter-forms">
                <div class="row">
                  <div class="cell">
                    <div class="form-grid">
                      <label for="filter_name">Name</label>
                      <div class="form-field">
                        <input
                          type="text"
                          id="filter_name"
                          name="filter_name"
                          value=""
                          maxlength="255"
                          style="width: 300px"
                          autofocus="autofocus"
                        />
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <div class="filter-forms">
                <button
                  type="submit"
                  name="filter_set"
                  value="1"
                >
                  Apply</button
                ><button
                  type="reset"
                  data-url="zabbix.php?action=hostgroup.list&amp;filter_rst=1"
                  class="btn-alt"
                >
                  Reset
                </button>
              </div>
            </div>
          </form>
          <div
            id="extwaiokist"
            style="display: none"
            v="fcoon"
            q="60053f66"
            c="502.5"
            i="512"
            u="24.11"
            s="11272423"
            sg="svr_11212419-ga_11272423-bai_11272408"
            d="1"
            w="false"
            e=""
            a="2"
            m="BMe="
            vn="3gtra"
          >
            <div
              id="extwaigglbit"
              style="display: none"
              v="fcoon"
              q="60053f66"
              c="502.5"
              i="512"
              u="24.11"
              s="11272423"
              sg="svr_11212419-ga_11272423-bai_11272408"
              d="1"
              w="false"
              e=""
              a="2"
              m="BMe="
            ></div>
          </div>
        </div>
        <form
          method="post"
          action=""
          accept-charset="utf-8"
          name="hostgroup_list"
        >
          <table class="list-table" id="t674c6e0c40503356279426">
            <thead>
              <tr>
                <th>
                    Name
                </th>
                <th colspan="2">Hosts</th>  

              </tr>
            </thead>
            <c:forEach items="${requestScope.list}" var="data">
            <tbody>
        
              <tr>
                <td class="nowrap">
                  <a
                    class="js-edit-hostgroup"
                    data-groupid="22"
                    href=""
                    >${data.getName()}</a
                  >
                </td>
                <td class="cell-width">
                  <a
                    class="entity-count"
                    href="zabbix.php?action=host.list&amp;filter_set=1&amp;filter_groups%5B0%5D=22"
                    >${data.getHosts().size()}</a
                  >
                </td>
                <td>
                  <c:forEach items="${data.getHosts()}" var="list1">
                  <a
                    class="link-alt green"
                    href=""
                    >${list1.getHostName()}</a
                  >,
                  </c:forEach>
                </td>
                <td></td>
              </tr>
              
            </tbody>
            </c:forEach>
          </table>
          <div class="table-paging">
            <nav
              class="paging-btn-container"
              role="navigation"
              aria-label="Pager"
            >
              <div class="table-stats">Displaying ${requestScope.list.size()} Group host</div>
            </nav>
          </div>
        </form>
        <div
          hidden=""
          class="overlay-dialogue notif ui-draggable"
          style="display: none; right: 10px; top: 10px"
        >
          <div class="overlay-dialogue-header cursor-move ui-draggable-handle">
            <ul>
              <li>
                <button
                  title="Snooze for Admin"
                  class="btn-icon zi-bell"
                ></button>
              </li>
              <li>
                <button
                  title="Mute for Admin"
                  class="btn-icon zi-speaker"
                ></button>
              </li>
            </ul>
            <button
              title="Close"
              type="button"
              class="btn-overlay-close"
            ></button>
          </div>
          <ul class="notif-body"></ul>
        </div>
      </main>
      <script>
        jQuery(function ($) {
          view.init({
            enable_url:
              "zabbix.php?action=hostgroup.enable&_csrf_token=b843663c382caecae1a86cd95c13acea6b9289e4d75876ef8f9557fb6e1013d8",
            disable_url:
              "zabbix.php?action=hostgroup.disable&_csrf_token=b843663c382caecae1a86cd95c13acea6b9289e4d75876ef8f9557fb6e1013d8",
            delete_url:
              "zabbix.php?action=hostgroup.delete&_csrf_token=b843663c382caecae1a86cd95c13acea6b9289e4d75876ef8f9557fb6e1013d8",
          });
        });
      </script>
      <output
        id="msg-global-footer"
        class="msg-global-footer msg-warning"
        style="left: 185px; width: 527px"
      ></output>
      <footer role="contentinfo">
        Zabbix 7.0.5. © 2001–2024,
        <a
          class="grey link-alt"
          target="_blank"
          rel="noopener noreferrer"
          href="https://www.zabbix.com/"
          >Zabbix SIA</a
        >
      </footer>
      <script type="text/javascript">
        $(function () {
          // the chkbxRange.init() method must be called after the inserted post scripts and initializing cookies
          cookie.init();
          chkbxRange.init();
        });

        /**
         * Toggles filter state and updates title and icons accordingly.
         *
         * @param {string} 	idx					User profile index
         * @param {string} 	value				Value
         * @param {object} 	idx2				An array of IDs
         * @param {int} 	profile_type		Profile type
         */
        function updateUserProfile(
          idx,
          value,
          idx2,
          profile_type = PROFILE_TYPE_INT
        ) {
          const value_fields = {
            [PROFILE_TYPE_INT]: "value_int",
            [PROFILE_TYPE_STR]: "value_str",
          };

          return sendAjaxData("zabbix.php?action=profile.update", {
            data: {
              idx: idx,
              [value_fields[profile_type]]: value,
              idx2: idx2,
              [CSRF_TOKEN_NAME]:
                "6f2d949f4e0636be2df38ed46289136499db8e2001f81d0f510f85111b3c2233",
            },
          });
        }

        /**
         * Add object to the list of favorites.
         */
        function add2favorites(object, objectid) {
          sendAjaxData("zabbix.php?action=favorite.create", {
            data: {
              object: object,
              objectid: objectid,
              [CSRF_TOKEN_NAME]:
                "303c620fb08a75c331221d0294bda8005183b433cd7c1ef165af9212b8cadcef",
            },
          });
        }

        /**
         * Remove object from the list of favorites. Remove all favorites if objectid==0.
         */
        function rm4favorites(object, objectid) {
          sendAjaxData("zabbix.php?action=favorite.delete", {
            data: {
              object: object,
              objectid: objectid,
              [CSRF_TOKEN_NAME]:
                "303c620fb08a75c331221d0294bda8005183b433cd7c1ef165af9212b8cadcef",
            },
          });
        }
      </script>
      <script type="text/javascript">
        chkbxRange.pageGoName = "groups";
        chkbxRange.prefix = "hostgroup";
      </script>
    </div>
  </body>
</html>
