export function getImageUrl(imagePath) {
    if (imagePath != ((`/assets/nike.jpg`) || (`/assets/monitor.jpg`))) {
        return `/assets/p1.jpg`;
    }
    return `/assets/${imagePath}`;
}