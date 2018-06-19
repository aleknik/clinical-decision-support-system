import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";

import { AppComponent } from "./app.component";
import { AuthModule } from "./auth/auth.module";
import { CoreModule } from "./core/core.module";
import { RoutesModule } from "./routes/routes.module";
import { SharedModule } from "./shared/shared.module";

@NgModule({
  declarations: [AppComponent],
  imports: [BrowserModule, AuthModule, CoreModule, RoutesModule, SharedModule],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {}