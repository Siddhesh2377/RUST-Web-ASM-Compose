use axum::{routing::get, Router};
use serde::Serialize;
use tower_http::cors::{CorsLayer, Any};

#[derive(Serialize)]
struct Message {
    text: String,
}

#[tokio::main]
async fn main() {
    let app = Router::new()
        .route("/api/hello", get(hello))
        .layer(CorsLayer::new().allow_origin(Any));

    let listener = tokio::net::TcpListener::bind("127.0.0.1:8080")
        .await
        .unwrap();

    println!("Listening on http://127.0.0.1:8080");
    axum::serve(listener, app).await.unwrap();
}

async fn hello() -> axum::Json<Message> {
    axum::Json(Message {
        text: "Hello from Rust backend!".to_owned(),
    })
}
