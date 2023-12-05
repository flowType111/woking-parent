package com.woke.working.common.constant;

public class AuthorityCodeConstant {

    /**
     * 系统管理
     */
    public class SystemManageCatalog {

        public static final String catalog = "system_manage_catalog";

        /**
         * 用户管理
         */
        public class SystemUserManage {
            public static final String menu = catalog + ":system_user_manage_menu";

            /**
             * 按钮
             * 按钮button
             */
            public class Button {
                public static final String add = menu + ":button_add";
                public static final String delete = menu + ":button_delete";
                public static final String update = menu + ":button_update";
                public static final String select = menu + ":button_select";
                public static final String details = menu + ":button_details";
            }
        }

        /**
         * 用户角色管理
         *
         */
        public class SystemUserRoleManage{
            public static final String menu = catalog + ":system_user_role_manage_menu";

            /**
             * 按钮
             * 按钮button
             */
            public class Button {
                public static final String add = menu + ":button_add";
                public static final String delete = menu + ":button_delete";
                public static final String update = menu + ":button_update";
                public static final String select = menu + ":button_select";
                public static final String details = menu + ":button_details";
            }
        }

        /**
         * 用户角色权限点管理
         *
         */
        public class SystemUserRoleMenuManage{
            public static final String menu = catalog + ":system_user_role_menu_manage_menu";

            /**
             * 按钮
             * 按钮button
             */
            public class Button {
                public static final String add = menu + ":button_add";
                public static final String delete = menu + ":button_delete";
                public static final String update = menu + ":button_update";
                public static final String select = menu + ":button_select";
            }
        }
    }
}
