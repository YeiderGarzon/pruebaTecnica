resource "azurerm_mssql_database_extended_auditing_policy" "res-44" {
  database_id            = "/subscriptions/09006557-08cb-4b06-aa78-4914e0f84092/resourceGroups/Yeider/providers/Microsoft.Sql/servers/prueba-tecnica-db/databases/prueba-tecnica-db"
  enabled                = false
  log_monitoring_enabled = false
  depends_on = [
    azurerm_mssql_database.res-33,
  ]
}
resource "azurerm_mssql_server" "res-12" {
  administrator_login = "CloudSA80d0e00c"
  location            = "westus2"
  name                = "prueba-tecnica-db"
  resource_group_name = "Yeider"
  version             = "12.0"
  azuread_administrator {
    login_username = "yeider.garzong@comunidad.iush.edu.co"
    object_id      = "bcd54249-d6b0-4fba-b9f5-d3ccf2350f76"
  }
}
resource "azurerm_mssql_firewall_rule" "res-54" {
  end_ip_address   = "74.220.48.242"
  name             = "ClientIPAddress_2026-1-13_5-31-56"
  server_id        = "/subscriptions/09006557-08cb-4b06-aa78-4914e0f84092/resourceGroups/Yeider/providers/Microsoft.Sql/servers/prueba-tecnica-db"
  start_ip_address = "74.220.48.242"
  depends_on = [
    azurerm_mssql_server.res-12,
  ]
}
resource "azurerm_mssql_database_extended_auditing_policy" "res-27" {
  database_id            = "/subscriptions/09006557-08cb-4b06-aa78-4914e0f84092/resourceGroups/Yeider/providers/Microsoft.Sql/servers/prueba-tecnica-db/databases/master"
  enabled                = false
  log_monitoring_enabled = false
}
resource "azurerm_mssql_server_microsoft_support_auditing_policy" "res-50" {
  enabled                = false
  log_monitoring_enabled = false
  server_id              = "/subscriptions/09006557-08cb-4b06-aa78-4914e0f84092/resourceGroups/Yeider/providers/Microsoft.Sql/servers/prueba-tecnica-db"
  depends_on = [
    azurerm_mssql_server.res-12,
  ]
}
resource "azurerm_mssql_server_transparent_data_encryption" "res-51" {
  server_id = "/subscriptions/09006557-08cb-4b06-aa78-4914e0f84092/resourceGroups/Yeider/providers/Microsoft.Sql/servers/prueba-tecnica-db"
  depends_on = [
    azurerm_mssql_server.res-12,
  ]
}
resource "azurerm_mssql_server_extended_auditing_policy" "res-52" {
  enabled                = false
  log_monitoring_enabled = false
  server_id              = "/subscriptions/09006557-08cb-4b06-aa78-4914e0f84092/resourceGroups/Yeider/providers/Microsoft.Sql/servers/prueba-tecnica-db"
  depends_on = [
    azurerm_mssql_server.res-12,
  ]
}
resource "azurerm_mssql_firewall_rule" "res-53" {
  end_ip_address   = "0.0.0.0"
  name             = "AllowAllWindowsAzureIps"
  server_id        = "/subscriptions/09006557-08cb-4b06-aa78-4914e0f84092/resourceGroups/Yeider/providers/Microsoft.Sql/servers/prueba-tecnica-db"
  start_ip_address = "0.0.0.0"
  depends_on = [
    azurerm_mssql_server.res-12,
  ]
}
resource "azurerm_sql_active_directory_administrator" "res-13" {
  login               = "yeider.garzong@comunidad.iush.edu.co"
  object_id           = "bcd54249-d6b0-4fba-b9f5-d3ccf2350f76"
  resource_group_name = "Yeider"
  server_name         = "prueba-tecnica-db"
  tenant_id           = "dbb9149e-ac6c-43c3-90ac-91a4b58519bd"
  depends_on = [
    azurerm_mssql_server.res-12,
  ]
}
resource "azurerm_mssql_database" "res-33" {
  name                 = "prueba-tecnica-db"
  server_id            = "/subscriptions/09006557-08cb-4b06-aa78-4914e0f84092/resourceGroups/Yeider/providers/Microsoft.Sql/servers/prueba-tecnica-db"
  storage_account_type = "Local"
  depends_on = [
    azurerm_mssql_server.res-12,
  ]
}
resource "azurerm_mssql_firewall_rule" "res-55" {
  end_ip_address   = "191.95.147.146"
  name             = "ClientIPAddress_2026-1-13_5-41-12"
  server_id        = "/subscriptions/09006557-08cb-4b06-aa78-4914e0f84092/resourceGroups/Yeider/providers/Microsoft.Sql/servers/prueba-tecnica-db"
  start_ip_address = "191.95.147.146"
  depends_on = [
    azurerm_mssql_server.res-12,
  ]
}
resource "azurerm_mssql_server_security_alert_policy" "res-57" {
  resource_group_name = "Yeider"
  server_name         = "prueba-tecnica-db"
  state               = "Disabled"
  depends_on = [
    azurerm_mssql_server.res-12,
  ]
}
resource "azurerm_mssql_server_vulnerability_assessment" "res-59" {
  server_security_alert_policy_id = "/subscriptions/09006557-08cb-4b06-aa78-4914e0f84092/resourceGroups/Yeider/providers/Microsoft.Sql/servers/prueba-tecnica-db/securityAlertPolicies/Default"
  storage_container_path          = ""
  depends_on = [
    azurerm_mssql_server_security_alert_policy.res-57,
  ]
}
